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
<<<<<<< HEAD
    apiclient.showWords((req, resp) => {
=======

    apiclient.showWords(word,view, (req, resp) => {
        console.log("qqqqqqqqqqqq");
        console.log("req: "+req);
        console.log("resp "+resp);
>>>>>>> a12c2930f4721d3b9bfcc5aeeab869cee50a546e
        createDataTable(resp);
    });
  }

  function createDataTable(data){
    let table = $("#fl-table tbody");
    table.empty();
<<<<<<< HEAD
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
=======
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
>>>>>>> a12c2930f4721d3b9bfcc5aeeab869cee50a546e
    } else {
        alert("Data not found!");
    }
  }

  return {
	  addWordTyped:addWordTyped,
	  showWords: showWords
  }

})();