using System;

namespace Ginder.Domain.Entities
{
    public class User : Entity
    {
        public Guid PlayerId { get; set; }
        
        public string Login { get; set; }
        public string Password { get; set; }

        public virtual Player Player { get; set; }
    }
}