using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using PictionaryParty.Models;
using PictionaryParty.Models.Entities;
using PictionaryParty.Models.Request.Words;
using PictionaryParty.Models.Response;
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
        /// <response code="200">Returns only the Words</response> 
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

        /// <summary>
        /// Words
        /// </summary>
        /// <response code="200">Add a word</response>
        [Authorize]
        [HttpPost("")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(WordResponse), StatusCodes.Status200OK)]
        public IActionResult AddWord([FromBody] CreateWordRequest addWordRequest)
        {
            var word = new Word
            {
                Category = addWordRequest.Category,
                English = addWordRequest.English,
                Italian = addWordRequest.Italian
            };
            DB.Words.Add(word);
            DB.SaveChanges();
            return Ok(word);
        }

        /// <summary>
        /// Words
        /// </summary>
        /// <response code="200">Return 200 if the word was delete</response>
        [Authorize]
        [HttpDelete("{id}")]
        [Produces("application/json")]
        [ProducesResponseType(typeof(bool), StatusCodes.Status200OK)]
        public IActionResult DeleteWord(int id)
        {
            var word = DB.Words.Find(id);
            if (word == null)
            {
                return NotFound();
            }
            word.DeletedAt = DateTime.Now;
            DB.SaveChanges();
            return Ok(true);
        }
        /// <summary>
        /// Words
        /// </summary>
        /// <response code="200">Returns all the  Words</response> 
        [HttpGet("all")]
        [ProducesResponseType(typeof(List<Word>), StatusCodes.Status200OK)]
        public IActionResult GetWords()
        {
            var words = DB.Words.Where(el => el.DeletedAt == null);
            return Ok(words);
        }

    }


}
