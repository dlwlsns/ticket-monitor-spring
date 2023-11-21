
var container = document.getElementsByClassName("list-group-item").item(0);

document.getElementById("search").oninput = function () {
    if(document.getElementById("search").value.length >= 3 || document.getElementById("search").value.length == 0){
        document.getElementById("searchBtn").click();
    }
}

document.getElementById("searchForm").onsubmit = function () {
    document.getElementById("searchBtn").click();
    return false;
}

document.getElementById("searchBtn").onclick = async function () {
    var context = document.querySelector('base').getAttribute('href');
    var url = context + '/ticket/search?q=' + document.getElementById('search').value;
    var options = {
        method : "GET",
        headers: {'Content-Type': 'application/json'}
    };

    await fetch(url, options)
        .then(function(response){
            if(!response.ok) {
                throw new Error('HTTP error status ' + response.status);
            }
            return response;
        })
        .then(function(response){
            return response.json();
        })
        .then(function(elements){
            document.getElementsByClassName("list-group").item(0).innerHTML = '';
            for (var i = 0; i < elements.length; i++) {
                container.querySelector("#title").innerHTML = elements[i].title;
                var words = elements[i].status.split('_');
                for (let i = 0; i < words.length; i++) {
                    words[i] = words[i].charAt(0) + words[i].slice(1).toLowerCase();
                }
                container.querySelector("#status").innerHTML = words.join(' ');
                container.querySelector("#id").innerHTML = "#" + elements[i].id;
                var link = container.querySelector("#id").href;
                container.querySelector("#id").href = link.substr(0, link.lastIndexOf("/")+1) + elements[i].id;
                container.querySelector("#author").innerHTML = elements[i].author.name + " " + elements[i].author.surname;
                container.querySelector("#date").innerHTML = elements[i].date;
                container.querySelector("#description").innerHTML = elements[i].description.slice(0,20) + (elements[i].description.length > 20 ? '...' : '');

                document.getElementsByClassName("list-group").item(0).appendChild(container.cloneNode(true));
            }
        })
};