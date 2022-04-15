using Microsoft.AspNetCore.Mvc;

namespace order.api.Controllers
{
    public class HealthController : Controller
    {
        [Route("api/[controller]")]
        public IActionResult Get()
        {
            return Ok(new { message = "App is running!" });
        }
    }
}
