namespace Ginder.Domain.Commands.LoginCommands
{
    public class LoginCommand : ICommand<bool>
    {
        public string Login { get; set; }
        public string Password { get; set; }
    }
}