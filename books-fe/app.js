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
    let loadBooksBtn = document.getElementById('loadBooks');
    loadBooksBtn.addEventListener('click', loadBooksBtnClickHandler);

    let tableBody = document.getElementById('books-container');

    async function loadBooksBtnClickHandler() {
        let res = await fetch('http://localhost:8000/api/books');
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
        editBtn.addEventListner('click', editHandler);
        cellBtns.appendChild(editBtn);

        let deleteBtn = document.createElement('button');
        deleteBtn.textContent = 'DELETE';
        deleteBtn.addEventListener('click', deleteHandler);
        cellBtns.appendChild(deleteBtn);

        tableRow.appendChild(cellBtns);

        tableBody.appendChild(tableRow);
    }

    function editHandler(event) {
                
    }

    function deleteHandler(event) {

    }

}