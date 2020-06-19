using System;

namespace Ginder.Domain.Entities
{
    public class PlayerGame : Entity
    {
        public Guid PlayerId { get; set; }
        public Guid GameId { get; set; }

        public string Description { get; set; }
        public string Role { get; set; }
        public string PeriodTime { get; set; }

        public virtual Player Player { get; set; }
        public virtual Game Game { get; set; }
    }
}