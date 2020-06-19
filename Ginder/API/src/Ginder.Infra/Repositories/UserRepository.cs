using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;

namespace Ginder.Infra.Repositories
{
    public class UserRepository : BaseRepository<User>, IUserRepository
    {
        public UserRepository(GinderDbContext applicationContext) : base(applicationContext)
        {
        }
    }
}