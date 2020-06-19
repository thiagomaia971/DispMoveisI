using System;
using System.Linq;
using System.Threading.Tasks;
using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Ginder.Infra.Repositories
{
    public abstract class BaseRepository<T> : IRepository<T> where T : Entity
    {
        protected readonly GinderDbContext ApplicationContext;
        protected DbSet<T> Itens { get; private set; }

        protected BaseRepository(GinderDbContext applicationContext)
        {
            ApplicationContext = applicationContext;
            Itens = ApplicationContext.Set<T>();
        }

        public void Insert(T entity) 
            => Itens.Add(entity);

        public void Update(T entity)
        {
            if (ApplicationContext.Entry(entity).IsKeySet)
            {
                var existingEntity = Find(entity.Id);
                ApplicationContext.Entry(existingEntity).CurrentValues.SetValues(entity);
            }
            else
            {
                Itens.Update(entity);
            }
        }

        public T Find(Guid id) 
            => ApplicationContext.Find<T>(id);

        public IQueryable<T> GetAll() 
            => Itens.AsQueryable();

        public void Delete(Guid id) 
            => Itens.Remove(Find(id));

        public void Delete(T entity) 
            => Itens.Remove(entity);
    }
}