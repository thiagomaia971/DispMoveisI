using Ginder.Domain.Commands;
using Ginder.Domain.Commands.GameCommands;
using Microsoft.AspNetCore.Mvc;

namespace Ginder.API.Controllers
{
    public class GamesController : BaseController
    {
        [HttpGet]
        public IActionResult Get([FromRoute] GamesCommand loginCommand) 
            => SendCommandRequest(loginCommand);
    }
}