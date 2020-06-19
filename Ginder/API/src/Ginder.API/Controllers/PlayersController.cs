using Ginder.Domain.Commands.PlayerCommands;
using Microsoft.AspNetCore.Mvc;

namespace Ginder.API.Controllers
{
    public class PlayersController : BaseController
    {
        [HttpGet]
        public IActionResult Get([FromRoute] PlayersCommand playersCommand) 
            => SendCommandRequest(playersCommand);
        
        [HttpGet]
        [Route("{PlayerId}")]
        public IActionResult GetSingle([FromRoute] PlayerCommand playerCommand) 
            => SendCommandRequest(playerCommand);
    }
}