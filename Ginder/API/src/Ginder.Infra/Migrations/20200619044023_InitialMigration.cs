using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Ginder.Infra.Migrations
{
    public partial class InitialMigration : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Games",
                columns: table => new
                {
                    Id = table.Column<Guid>(nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(nullable: false),
                    UpdatedAt = table.Column<DateTimeOffset>(nullable: true),
                    DeletedAt = table.Column<DateTimeOffset>(nullable: true),
                    Name = table.Column<string>(nullable: true),
                    ImagePath = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Games", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "Players",
                columns: table => new
                {
                    Id = table.Column<Guid>(nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(nullable: false),
                    UpdatedAt = table.Column<DateTimeOffset>(nullable: true),
                    DeletedAt = table.Column<DateTimeOffset>(nullable: true),
                    Name = table.Column<string>(nullable: true),
                    Idade = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Players", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "PlayerGames",
                columns: table => new
                {
                    Id = table.Column<Guid>(nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(nullable: false),
                    UpdatedAt = table.Column<DateTimeOffset>(nullable: true),
                    DeletedAt = table.Column<DateTimeOffset>(nullable: true),
                    PlayerId = table.Column<Guid>(nullable: false),
                    GameId = table.Column<Guid>(nullable: false),
                    Description = table.Column<string>(nullable: true),
                    Role = table.Column<string>(nullable: true),
                    PeriodTime = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PlayerGames", x => x.Id);
                    table.ForeignKey(
                        name: "FK_PlayerGames_Games_GameId",
                        column: x => x.GameId,
                        principalTable: "Games",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_PlayerGames_Players_PlayerId",
                        column: x => x.PlayerId,
                        principalTable: "Players",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    Id = table.Column<Guid>(nullable: false),
                    CreatedAt = table.Column<DateTimeOffset>(nullable: false),
                    UpdatedAt = table.Column<DateTimeOffset>(nullable: true),
                    DeletedAt = table.Column<DateTimeOffset>(nullable: true),
                    PlayerId = table.Column<Guid>(nullable: false),
                    Login = table.Column<string>(nullable: true),
                    Password = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.Id);
                    table.ForeignKey(
                        name: "FK_Users_Players_PlayerId",
                        column: x => x.PlayerId,
                        principalTable: "Players",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "Games",
                columns: new[] { "Id", "CreatedAt", "DeletedAt", "ImagePath", "Name", "UpdatedAt" },
                values: new object[,]
                {
                    { new Guid("8e4275ae-b5ed-4a16-a8c7-32d4c5cbd4f4"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 954, DateTimeKind.Unspecified).AddTicks(9157), new TimeSpan(0, -3, 0, 0, 0)), null, "/Games/LeagueOfLegends.png", "League of Legends", null },
                    { new Guid("885c76df-b632-496e-a87f-98a5a362bd94"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 954, DateTimeKind.Unspecified).AddTicks(9230), new TimeSpan(0, -3, 0, 0, 0)), null, "/Games/WorldOfWarcraft.png", "World of Warcraft", null },
                    { new Guid("71833138-ba80-4851-bf9d-ff01ea572d88"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 954, DateTimeKind.Unspecified).AddTicks(9238), new TimeSpan(0, -3, 0, 0, 0)), null, "/Games/ShadowArena.png", "Shadow Arena", null }
                });

            migrationBuilder.InsertData(
                table: "Players",
                columns: new[] { "Id", "CreatedAt", "DeletedAt", "Idade", "Name", "UpdatedAt" },
                values: new object[] { new Guid("c8413c17-63e7-491a-9eae-0489737e19ff"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 953, DateTimeKind.Unspecified).AddTicks(5168), new TimeSpan(0, -3, 0, 0, 0)), null, 20, "Admin Teste Player", null });

            migrationBuilder.InsertData(
                table: "PlayerGames",
                columns: new[] { "Id", "CreatedAt", "DeletedAt", "Description", "GameId", "PeriodTime", "PlayerId", "Role", "UpdatedAt" },
                values: new object[] { new Guid("fee6b24d-a74d-4391-ab0e-091d48762b6f"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 955, DateTimeKind.Unspecified).AddTicks(1582), new TimeSpan(0, -3, 0, 0, 0)), null, "Jogo pra me divertir", new Guid("8e4275ae-b5ed-4a16-a8c7-32d4c5cbd4f4"), "Jogo aos finais de semanas", new Guid("c8413c17-63e7-491a-9eae-0489737e19ff"), "Jogo na posição de ADC", null });

            migrationBuilder.InsertData(
                table: "Users",
                columns: new[] { "Id", "CreatedAt", "DeletedAt", "Login", "Password", "PlayerId", "UpdatedAt" },
                values: new object[] { new Guid("c6699c75-5f44-4029-a404-84ce8a3951c8"), new DateTimeOffset(new DateTime(2020, 6, 19, 1, 40, 10, 954, DateTimeKind.Unspecified).AddTicks(7998), new TimeSpan(0, -3, 0, 0, 0)), null, "admin", "123", new Guid("c8413c17-63e7-491a-9eae-0489737e19ff"), null });

            migrationBuilder.CreateIndex(
                name: "IX_PlayerGames_GameId",
                table: "PlayerGames",
                column: "GameId");

            migrationBuilder.CreateIndex(
                name: "IX_PlayerGames_PlayerId",
                table: "PlayerGames",
                column: "PlayerId");

            migrationBuilder.CreateIndex(
                name: "IX_Users_PlayerId",
                table: "Users",
                column: "PlayerId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "PlayerGames");

            migrationBuilder.DropTable(
                name: "Users");

            migrationBuilder.DropTable(
                name: "Games");

            migrationBuilder.DropTable(
                name: "Players");
        }
    }
}
