import env from './env'

const DEV_URL = 'http://139.159.221.235:9009'
const PRO_URL = 'http://139.159.221.235:9009'

export default env === 'development' ? DEV_URL : PRO_URL
