package Atividade02
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

fun main (args: Array<String>) {
//    val vetor: Array<CiaAerea> = Array<CiaAerea>(5) {
//        Tam()
//        Gol()
//        AmericanAirlines()
//        randomCiaAerea()
//        randomCiaAerea()
//    }
    val vetor = listOf(
        Tam(),
        Gol(),
        AmericanAirlines(),
        randomCiaAerea(),
        randomCiaAerea())

    for (item: CiaAerea in vetor) {
        item.emitirBilhete(LocalDate.now(), BigDecimal.valueOf(10))
    }

    println("")

    for (item: CiaAerea in vetor) {
        if (item is AmericanAirlines)
            (item as AmericanAirlines).informarInfraero()
    }
}

fun randomCiaAerea(): CiaAerea {
    val randomValue =  (0..2).shuffled().first()
    if (randomValue == 0)
        return Tam()
    if (randomValue == 1)
        return Gol()
    if (randomValue == 2)
        return AmericanAirlines()

    return Tam()
}
class Bilhete (val data: LocalDate)
interface CiaAerea {
    fun emitirBilhete(data: LocalDate, valor: BigDecimal): Bilhete
}

class Tam : CiaAerea {
    override fun emitirBilhete(data: LocalDate, valor: BigDecimal): Bilhete {
        println("Tam.emitirBilhete()")
        return Bilhete(data)
    }
}

class Gol : CiaAerea {
    override fun emitirBilhete(data: LocalDate, valor: BigDecimal): Bilhete {
        println("Gol.emitirBilhete()")
        return Bilhete(data)
    }
}

class AmericanAirlines : CiaAerea {
    override fun emitirBilhete(data: LocalDate, valor: BigDecimal): Bilhete {
        println("AmericanAirlines.emitirBilhete()")
        return Bilhete(data)
    }

    fun informarInfraero() {
        println("AmericanAirlines.informarInfraero()")
    }
}