using System;
using System.Linq;
using System.Threading.Tasks;
using Ginder.Domain.Entities;

namespace Ginder.Domain.Repositories
{
    public interface IRepository<T> where T : Entity
    {
        void Insert(T entity);
        void Update(T entity);
        T Find(Guid id);
        IQueryable<T> GetAll();
        void Delete(Guid id);
        void Delete(T entity);
    }
}