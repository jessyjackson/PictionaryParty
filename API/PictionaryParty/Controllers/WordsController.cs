using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using PictionaryParty.Models;
using PictionaryParty.Models.Entities;
using PictionaryParty.Models.Request.Words;
using System.ComponentModel.DataAnnotations;

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
            if (wordsRequest.Category != null)
            {
                words = words.Where(el => el.Category == wordsRequest.Category);
            }
            if (wordsRequest.Language == "it")
            {
                var w = words.Select(el => el.Italian);
                return Ok(w);
            }
            else if (wordsRequest.Language == "en")
            {
                var w = words.Select(el => el.English);
                return Ok(w);
            }
            var defaults = words.Select(el => el.English);
            return Ok(defaults);   
        }
    }


}
