//             <tr>
//                 <td>Harry Poter</td>
//                 <td>J. K. Rowling</td>
//                 <td>0-7475-3269-9</td>
//                 <td>
//                     <button>Edit</button>
//                     <button>Delete</button>
//                 </td>
//             </tr>

window.addEventListener('load', solve);
function solve() {
    const BASE_URL = 'http://localhost:8000/api/books';
    let loadBooksBtn = document.getElementById('loadBooks');
    loadBooksBtn.addEventListener('click', loadBooksBtnClickHandler);

    let tableBody = document.getElementById('books-container');

    let formInputs = {
        title: () => document.getElementById('title'),
        authorName: () => document.getElementById('author'),
        isbn: () => document.getElementById('isbn')
    }
    let submitBtn = document.querySelector("form > button");

    clearFormInputs();

    submitBtn.addEventListener('click', submitClickHandler);

    async function loadBooksBtnClickHandler() {
        let res = await fetch(`${BASE_URL}`);
        let books = await res.json();
        tableBody.innerHTML = '';

        books.forEach(b => createRow(b));

    }

    function createRow(book) {
        // <th>Title</th>
        // <th>Author</th>
        // <th>Isbn</th>
        // <th>Action</th>
        let tableRow = document.createElement('tr');
        tableRow.dataset.id = book.id;

        let titleCell = document.createElement('td');
        titleCell.textContent = book.title;
        tableRow.appendChild(titleCell);

        let authorCell = document.createElement('td');
        authorCell.textContent = book.authorName;
        tableRow.appendChild(authorCell);

        let isbnCell = document.createElement('td');
        isbnCell.textContent = book.isbn;
        tableRow.appendChild(isbnCell);

        let cellBtns = document.createElement('td');

        let editBtn = document.createElement('button');
        editBtn.textContent = 'EDIT';
        editBtn.addEventListener('click', editHandler);
        cellBtns.appendChild(editBtn);

        let deleteBtn = document.createElement('button');
        deleteBtn.textContent = 'DELETE';
        deleteBtn.addEventListener('click', deleteHandler);
        cellBtns.appendChild(deleteBtn);

        tableRow.appendChild(cellBtns);

        tableBody.appendChild(tableRow);
    }

    function editHandler(event) {
        let id = event.target.parentNode.parentNode.dataset.id;

        fetch(`${BASE_URL}/${id}`)
            .then((res) => res.json())
            .then((data) => fillInputsData(data));
        // debugger;
    }

    function deleteHandler(event) {
        let id = event.target.parentNode.parentNode.dataset.id;
        let http = {
            method: "DELETE"
        }
        fetch(`${BASE_URL}`, http)
            .then(() => loadBooksBtnClickHandler())
    }

    function submitClickHandler(event) {
        event.preventDefault();

        let data = getInputsData();
        let reqUrl = BASE_URL;
        let id = '';
        let http = {
            headers: {'Content-Type': 'application/json'},
            method: 'POST'
        };

        if ('id' in submitBtn.dataset) {
            id = submitBtn.dataset.id;
            http.method = 'PUT';
            reqUrl = `${BASE_URL}/${id}`
        }

        http.body = JSON.stringify(data);

        fetch(reqUrl, http)
            .then(() => {
                clearFormInputs();
                loadBooksBtnClickHandler();
            });
    }

    function getInputsData() {
        let data = {};
        for (const inp in formInputs) {
            data[inp] = formInputs[inp]().value;
        }

        return data;
    }

    function fillInputsData(data) {
        for (const inp in formInputs) {
            formInputs[inp]().value = data[inp];
        }
        submitBtn.dataset.id = data.id;
    }

    function clearFormInputs() {
        for (const inp in formInputs) {
            formInputs[inp]().value = '';
        }

        delete submitBtn.dataset.id;
    }

}