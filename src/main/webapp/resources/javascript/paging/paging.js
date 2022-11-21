const getDataAndDraw = async (url, dataRow, tableId, bodyId, pageComponent, paginationId, page = 1) => {
    page -= 1;

    const json = await (await fetch(url + page)).json();

    if (json.data.length != 0) {
        const newTbody = document.createElement("tbody");
        newTbody.setAttribute("id", bodyId);
        json.data.forEach(data => newTbody.innerHTML += dataRow(data));

        const table = document.getElementById(tableId);
        const oldTbody = document.getElementById(bodyId);
        table.replaceChild(newTbody, oldTbody);
    }

    const pagination = document.getElementById(paginationId);

    let pageBlockSize = 3;
    let currentPage = page + 1;
    let totalPages = json.pageInfo.totalPages;

    let currentBlock = Math.floor((currentPage - 1) / pageBlockSize);
    let startPage = currentBlock * pageBlockSize + 1;
    let endPage = startPage + pageBlockSize - 1;
    if (endPage > totalPages) {
        endPage = totalPages;
    }

    let pageBtn = "";

    if (currentBlock > 0) {
        pageBtn += pageComponent({
            "text": "이전",
            "pageNumber": startPage - pageBlockSize
        });
    }

    for (let i = startPage; i <= endPage; i++) {
        pageBtn += pageComponent({
            "text": i,
            "pageNumber": i,
            "bold": i === currentPage
        });
    }

    if (endPage !== totalPages) {
        pageBtn += pageComponent({
            "text": "다음",
            "pageNumber": startPage + pageBlockSize
        });
    }

    pagination.innerHTML = pageBtn;
}
