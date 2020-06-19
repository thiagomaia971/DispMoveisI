using System.Linq;
using Ginder.Domain.Commands.PlayersGameCommands;
using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Ginder.Domain.CommandHandlers.PlayersGameCommandHandlers
{
    public class PlayersGameCommandHandler : CommandHandler<PlayersGameCommand, IQueryable<PlayerGame>>
    {
        private readonly IPlayerGameRepository _playerGameRepository;

        public PlayersGameCommandHandler(IPlayerGameRepository playerGameRepository) 
            => _playerGameRepository = playerGameRepository;

        public override IQueryable<PlayerGame> Handle(PlayersGameCommand command) 
            => _playerGameRepository.GetAll()
                .Where(x => x.GameId == command.GameId)
                .Include(x => x.Player);
    }
}