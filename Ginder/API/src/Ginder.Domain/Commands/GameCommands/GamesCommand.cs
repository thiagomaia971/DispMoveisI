using System.Linq;
using Ginder.Domain.Entities;

namespace Ginder.Domain.Commands.GameCommands
{
    public class GamesCommand : ICommand<IQueryable<Game>>
    {
    }
}