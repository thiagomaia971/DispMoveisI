using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;

namespace Ginder.Infra.Repositories
{
    public class GameRepository : BaseRepository<Game>, IGameRepository
    {
        public GameRepository(GinderDbContext applicationContext) : base(applicationContext)
        {
        }
    }
}