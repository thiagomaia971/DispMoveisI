using System.Linq;
using Ginder.Domain.Commands.PlayerCommands;
using Ginder.Domain.Entities;
using Ginder.Domain.Repositories;
using Microsoft.EntityFrameworkCore;

namespace Ginder.Domain.CommandHandlers.PlayerCommandHandlers
{
    public class PlayersCommandHandler : CommandHandler<PlayersCommand, IQueryable<Player>>
    {
        private readonly IPlayerRepository _playerRepository;

        public PlayersCommandHandler(IPlayerRepository playerRepository) 
            => _playerRepository = playerRepository;

        public override IQueryable<Player> Handle(PlayersCommand command) 
            => _playerRepository.GetAll().Include(x => x.Games);
    }
}