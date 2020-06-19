using System.Linq;
using Ginder.Domain.Entities;

namespace Ginder.Domain.Commands.PlayerCommands
{
    public class PlayersCommand : ICommand<IQueryable<Player>>
    {
    }
}