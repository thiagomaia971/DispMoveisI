using System.Windows.Input;
using Ginder.Domain.Commands;
using Ginder.Domain.Commands.LoginCommands;
using Microsoft.AspNetCore.Mvc;

namespace Ginder.API.Controllers
{
    public class UsersController : BaseController
    {
        [HttpGet]
        [Route("login/{Login}/{Password}")]
        public IActionResult Login([FromRoute] LoginCommand loginCommand) 
            => SendCommandRequest(loginCommand);
    }
}