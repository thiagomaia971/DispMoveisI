using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;

namespace Ginder.Infra.Repositories
{
    public class PlayerGameRepository : BaseRepository<PlayerGame>, IPlayerGameRepository
    {
        public PlayerGameRepository(GinderDbContext applicationContext) : base(applicationContext)
        {
        }
    }
}