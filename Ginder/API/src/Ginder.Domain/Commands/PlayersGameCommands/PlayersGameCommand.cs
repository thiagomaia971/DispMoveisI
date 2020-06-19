using System;
using System.Linq;
using Ginder.Domain.Entities;

namespace Ginder.Domain.Commands.PlayersGameCommands
{
    public class PlayersGameCommand : ICommand<IQueryable<PlayerGame>>
    {
        public Guid GameId { get; set; }
    }
}