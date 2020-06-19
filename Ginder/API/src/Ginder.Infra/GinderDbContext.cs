using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using Ginder.Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace Ginder.Infra
{
    public class GinderDbContext : DbContext
    {
        #region Tables

        public DbSet<User> Users { get; set; }
        public DbSet<Game> Games { get; set; }
        public DbSet<Player> Players { get; set; }
        public DbSet<PlayerGame> PlayerGames { get; set; }

        #endregion

        public GinderDbContext(DbContextOptions<GinderDbContext> options) : base(options)
        {
            Database.Migrate();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // modelBuilder.ApplyConfigurationsFromAssembly(Assembly.GetAssembly(typeof(BaseEntityTypeConfiguration<>)));
            //
            // foreach (var entityType in modelBuilder.Model.GetEntityTypes().Where(e => (bool)e.ClrType?.IsAssignableFrom(typeof(BaseOwnerEntity))))
            // {
            //     Expression<Func<BaseOwnerEntity, bool>> filter = e => e.OwnerId == _ownerId;
            //     modelBuilder.Entity(entityType.ClrType).HasQueryFilter(filter);
            // }
            //
            
            modelBuilder.Entity<Player>().HasData(
                new Player
                {
                    Id = Guid.Parse("c8413c17-63e7-491a-9eae-0489737e19ff"), Name = "Admin Teste Player", Idade = 20,
                    CreatedAt = DateTimeOffset.Now
                }
            );
            
            modelBuilder.Entity<User>().HasData(
                new User { Id = Guid.Parse("C6699C75-5F44-4029-A404-84CE8A3951C8"), PlayerId = Guid.Parse("c8413c17-63e7-491a-9eae-0489737e19ff"), Login = "admin", Password = "123", CreatedAt = DateTimeOffset.Now }
            );
            
            modelBuilder.Entity<Game>().HasData(
                new Game { Id = Guid.Parse("8e4275ae-b5ed-4a16-a8c7-32d4c5cbd4f4"), Name = "League of Legends", ImagePath = "/Games/LeagueOfLegends.png", CreatedAt = DateTimeOffset.Now },
                new Game { Id = Guid.Parse("885c76df-b632-496e-a87f-98a5a362bd94"), Name = "World of Warcraft", ImagePath = "/Games/WorldOfWarcraft.png", CreatedAt = DateTimeOffset.Now },
                new Game { Id = Guid.Parse("71833138-ba80-4851-bf9d-ff01ea572d88"), Name = "Shadow Arena", ImagePath = "/Games/ShadowArena.png", CreatedAt = DateTimeOffset.Now }
            );

            modelBuilder.Entity<PlayerGame>().HasData(
                new PlayerGame
                {
                    Id = Guid.Parse("fee6b24d-a74d-4391-ab0e-091d48762b6f"),
                    PlayerId = Guid.Parse("c8413c17-63e7-491a-9eae-0489737e19ff"),
                    GameId = Guid.Parse("8e4275ae-b5ed-4a16-a8c7-32d4c5cbd4f4"), Description = "Jogo pra me divertir",
                    PeriodTime = "Jogo aos finais de semanas", Role = "Jogo na posição de ADC",
                    CreatedAt = DateTimeOffset.Now
                }
            );
            base.OnModelCreating(modelBuilder);
        }

        public override async Task<int> SaveChangesAsync(CancellationToken cancellationToken = new CancellationToken())
        {
            await UpdatePrivateFields();
            return await base.SaveChangesAsync(cancellationToken);
        }
        
        private async Task UpdatePrivateFields()
        {
            var dataAtual = DateTimeOffset.Now;
            foreach (var entity in ModifiedAndAddedEntities())
            {
                switch (entity.State)
                {
                    case EntityState.Deleted:
                        entity.CurrentValues["DeletedAt"] = dataAtual;
                        break;
                    case EntityState.Modified:
                        entity.CurrentValues["UpdatedAt"] = dataAtual;
                        break;
                    case EntityState.Added:
                    case EntityState.Detached:
                    case EntityState.Unchanged:
                        break;
                    default:
                        throw new ArgumentOutOfRangeException();
                }   
            }

            // await _domainEventEmitter.PublishAsync(PendingEvents().ToArray());
            // _eventsSent = _eventsSent.Concat(PendingEvents()).ToList();

            if (ModifiedAndAddedEntities().Any()/* || PendingEvents().Any()*/)
                await UpdatePrivateFields();
        }
        
        // private IEnumerable<IDomainEvent> PendingEvents() 
        //     => DomainEvents().Where(es => !_eventsSent.Contains(es));

        private IEnumerable<EntityEntry> EntityEntries()
        {
            var entities = ChangeTracker.Entries().Where(e => e.Entity is Entity);
            return entities;
        }

        private IEnumerable<EntityEntry> ModifiedAndAddedEntities()
        {
            var entities = EntityEntries();
            var modifiedAndAdded = entities.Where(e => (e.State == EntityState.Modified || e.State == EntityState.Added) &&
                                                       ((e.Entity as Entity).CreatedAt == DateTime.MinValue || (e.Entity as Entity).UpdatedAt == DateTime.MinValue));
            return modifiedAndAdded;
        }
    }
}