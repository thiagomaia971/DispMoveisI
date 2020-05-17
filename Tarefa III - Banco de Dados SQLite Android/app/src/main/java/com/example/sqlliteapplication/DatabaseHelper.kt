package com.example.sqlliteapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class Carro(val id: Int, val modelo: String, val estilo: String, val ano: Int, val proprietarioId: Int) {

}

class DatabaseHelper(context: Context)  : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    object PessoaReaderContract {
        object PessoaEntry : BaseColumns {
            const val TABLE_NAME = "Pessoa"
            const val COLUMN_NAME_ID = "ID"
            const val COLUMN_NAME_NOME = "Nome"
        }

        public const val SQL_CREATE_PESSOAS =
            "CREATE TABLE ${PessoaEntry.TABLE_NAME} (" +
                    "${PessoaEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${PessoaEntry.COLUMN_NAME_NOME} TEXT)"

        public const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PessoaEntry.TABLE_NAME}"
    }

    object CarroReaderContract {
        object CarroEntry : BaseColumns {
            const val TABLE_NAME = "Carro"
            const val COLUMN_NAME_ID = "ID"
            const val COLUMN_NAME_MODELO = "MODELO"
            const val COLUMN_NAME_ESTILO = "ESTILO"
            const val COLUMN_NAME_ANO = "ANO"
            const val COLUMN_NAME_PROPRIETARIO= "PROPRIETARIO"
        }

        public const val SQL_CREATE_CARRO =
            "CREATE TABLE ${CarroEntry.TABLE_NAME} (" +
                    "${CarroEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${CarroEntry.COLUMN_NAME_MODELO} TEXT," +
                    "${CarroEntry.COLUMN_NAME_ESTILO} TEXT," +
                    "${CarroEntry.COLUMN_NAME_ANO} INTEGER," +
                    "${CarroEntry.COLUMN_NAME_PROPRIETARIO} INTEGER," +
                    "FOREIGN KEY (${CarroEntry.COLUMN_NAME_PROPRIETARIO}) REFERENCES ${PessoaReaderContract.PessoaEntry.TABLE_NAME}(${PessoaReaderContract.PessoaEntry.COLUMN_NAME_ID})" +
                    ")"

        public const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CarroEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(PessoaReaderContract.SQL_CREATE_PESSOAS)
        db.execSQL(CarroReaderContract.SQL_CREATE_CARRO)

        if (ADICIONAR_VALORES){
            db.execSQL("INSERT INTO Pessoa (NOME) values ('Nicolas');")
            db.execSQL("INSERT INTO Pessoa (NOME) values ('Natan');")
            db.execSQL("INSERT INTO Pessoa (NOME) values ('Gledson');")
            db.execSQL("INSERT INTO Pessoa (NOME) values ('Vitor');")
            db.execSQL("INSERT INTO Pessoa (NOME) values ('Bruno');")

            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Opala', 'Sedan', 1979, 1);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Maverick', 'Esportivo', 1974, 2);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Caravan', 'Perua', 1980, 3);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Passat', 'Hatch', 1982, 4);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Corcel II', 'Sedan', 1982, 5);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Jeep', 'Off Road', 1955, 3);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Corolla', 'Sedan', 2016, 1);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Camaro', 'Esportivo', 2015, 2);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Spin', 'Perua', 2017, 3);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('HB20', 'Hatch', 2019, 4);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('A3', 'Sedan', 2018, 5);")
            db.execSQL("INSERT INTO Carro (MODELO, ESTILO, ANO, PROPRIETARIO) values ('Troller', 'Off Road', 1955, 3);")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(PessoaReaderContract.SQL_DELETE_ENTRIES)
        db.execSQL(CarroReaderContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun getProprietarioNome(proprietarioId: String): String {
        val query = "SELECT * FROM ${PessoaReaderContract.PessoaEntry.TABLE_NAME} WHERE ${PessoaReaderContract.PessoaEntry.COLUMN_NAME_ID} = ${proprietarioId}"

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()){
            var id = cursor.getInt(0)
            var nome = cursor.getString(1)
            cursor.close()
            return nome
        }

        return ""
    }

    fun getCarrosDoProprietario(proprietarioId: String): List<Carro> {
        val query = "SELECT * FROM ${CarroReaderContract.CarroEntry.TABLE_NAME} WHERE ${CarroReaderContract.CarroEntry.COLUMN_NAME_PROPRIETARIO} = ${proprietarioId}"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)

        val carros = mutableListOf<Carro>()

        while (cursor.moveToNext()){
            var id = cursor.getInt(0)
            var modelo = cursor.getString(1)
            var estilo = cursor.getString(2)
            var ano = cursor.getInt(3)

            carros.add(Carro(id, modelo, estilo, ano, proprietarioId.toInt()))
        }

        cursor.close()
        return carros
    }

    companion object {
        const val DATABASE_VERSION = 4
        const val DATABASE_NAME = "SQLiteApplication.db"
        const val ADICIONAR_VALORES = true
    }
}