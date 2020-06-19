using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;

namespace Ginder.Infra.Repositories
{
    public class PlayerRepository : BaseRepository<Player>, IPlayerRepository
    {
        public PlayerRepository(GinderDbContext applicationContext) : base(applicationContext)
        {
        }
    }
}