using Ginder.Domain.Commands.PlayersGameCommands;
using Microsoft.AspNetCore.Mvc;

namespace Ginder.API.Controllers
{
    public class PlayersGameController : BaseController
    {
        [HttpGet]
        [Route("{GameId}")]
        public IActionResult Get([FromRoute] PlayersGameCommand playersGameCommand) 
            => SendCommandRequest(playersGameCommand);
    }
}