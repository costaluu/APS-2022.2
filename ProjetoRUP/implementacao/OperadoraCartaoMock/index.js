const express = require('express')
const app = express()
const cors = require('cors')
const port = 8081

app.use(cors())

app.get('/pagamento', (req, res) => {
  console.log('Recebendo requisição de pagamento')
  res.status(200).send('Hello World!')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
