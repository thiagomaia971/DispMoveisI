* dotnet ef migrations add AddUserMigration --project ./src/Ginder.Infra --startup-project ./src/Ginder.API
* dotnet ef database update --project ./src/Ginder.Infra --startup-project ./src/Ginder.API