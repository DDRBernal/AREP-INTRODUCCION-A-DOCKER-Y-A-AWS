var app = (function () {
  var view = function(error, res) {
      $("#ok").text(res.confirm);
      return "callback";
    }

  function addWordTyped(word) {
      //const word = document.querySelector('word').value;
      alert("Word saved");
      apiclient.addWord(word);
  }

  function showWords(word){
    apiclient.showWords((req, resp) => {
        createDataTable(resp);
    });
  }

  function createDataTable(data){
    let table = $("#fl-table tbody");
    table.empty();
    if (data !== undefined) {
      datanew = data.split("},");
      datanew = datanew.slice(Math.max(datanew.length - 10, 0))
      datanew.forEach(function replace(item){
          item = item.replace("Document{{","{").replace("[","").replace("}]","");
          object = []
          let obj = JSON.stringify(item).split("=");
          let name = obj[2].replace("}","").replace('"',"") ;
          table.append(
                `<tr>
                  <td>${name}</td>
                `
            );
      });
    } else {
        alert("Data not found!");
    }
  }

  return {
	  addWordTyped:addWordTyped,
	  showWords: showWords
  }

})();