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

    apiclient.showWords(word,view, (req, resp) => {
        console.log("qqqqqqqqqqqq");
        console.log("req: "+req);
        console.log("resp "+resp);
        createDataTable(resp);
    });
  }

  function createDataTable(data){
    let table = $("#fl-table tbody");
    table.empty();
    console.log(data);
    if (data !== undefined) {
      const datanew = data.map((blueprint) => {
          return {
              id: blueprint._id,
              name: blueprint.points.length
          }
      });
      console.log(datanew);
      datanew.forEach(({id, name}) => {
      table.append(
                      `<tr>
                        <td>${id}</td>
                        <td>${name}</td>
                      `
                  );
              })
    } else {
        alert("Data not found!");
    }
  }

  return {
	  addWordTyped:addWordTyped,
	  showWords: showWords
  }

})();