using System.Linq;
using Ginder.Domain.Commands.GameCommands;
using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;

namespace Ginder.Domain.CommandHandlers.GameCommandHandlers
{
    public class GameCommandHandler : CommandHandler<GamesCommand, IQueryable<Game>>
    {
        private readonly IGameRepository _gameRepository;

        public GameCommandHandler(IGameRepository gameRepository) 
            => _gameRepository = gameRepository;

        public override IQueryable<Game> Handle(GamesCommand command) 
            => _gameRepository.GetAll();
    }
}