using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using PictionaryParty.Models;
using PictionaryParty.Models.Entities;
using PictionaryParty.Models.Request.Words;

namespace PictionaryParty.Controllers
{
    [ApiController]
    [Route("api/words")]
    public class WordsController : BaseController
    {
        public WordsController(PictionaryPartyDBContext context) : base(context)
        {
        }

        /// <summary>
        /// Words
        /// </summary>
        /// <response code="200">Returns the Words</response> 
        [HttpGet("")]
        public IActionResult GetWords([FromQuery] GetWordsRequest wordsRequest)
        {
            var words = DB.Words.Where(el => el.DeletedAt == null);
            if (wordsRequest.Language == "it")
            {
                var w = words.Select(el => el.Italian);
                return Ok(w);
            }
            return Ok(words);
            
        }
    }


}
