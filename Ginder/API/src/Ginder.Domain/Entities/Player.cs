using System.Collections.Generic;

namespace Ginder.Domain.Entities
{
    public class Player : Entity
    {
        public string Name { get; set; }
        public int Idade { get; set; }

        public virtual ICollection<PlayerGame> Games { get; set; }
    }
}