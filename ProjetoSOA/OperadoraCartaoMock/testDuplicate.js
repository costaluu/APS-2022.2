let arr = [
  {
    id: 1,
    name: 'a'
  },
  {
    id: 2,
    name: 'b'
  },
  {
    id: 3,
    name: 'a'
  }
]
let maped = arr.map(item => item.name);
console.log(maped);
let unique = new Set(maped);
console.log(unique);