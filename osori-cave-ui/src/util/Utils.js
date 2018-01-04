export const findUrl = (data, rel) => {
    let url = data.links.find(item => {
        return item.rel === rel
    }).href;

    if (url === undefined) {
        console.err('can\'t find $s url. links', rel);
    }
    return url;
}




