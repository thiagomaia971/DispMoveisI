using System;
using System.Linq;
using Ginder.Domain.Commands.LoginCommands;
using Ginder.Domain.Repositories;

namespace Ginder.Domain.CommandHandlers.LoginCommandHandlers
{
    public class LoginCommandHandler : CommandHandler<LoginCommand, bool>
    {
        private readonly IUserRepository _userRepository;

        public LoginCommandHandler(IUserRepository userRepository) 
            => _userRepository = userRepository;

        public override bool Handle(LoginCommand command)
        {
            var user = _userRepository.GetAll().FirstOrDefault(x => x.Login == command.Login);
            
            if (user.IsNull())
                throw new Exception("Usuário não encontrado.");
            
            if (user.Password != command.Password)
                throw new Exception("Senha inválida.");
            
            return true;
        }
    }

    public static class ObjectExtensions
    {
        public static bool IsNull(this Object value)
            => value == null;
    }
}