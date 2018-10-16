export const IS_MOCK = true
export const API_BASE_URL = IS_MOCK ? '/mock/' : (process.env.NODE_ENV === 'development' ? 'http://127.0.0.1:80' : 'http://www.xxx.com')
export const TOKEN_KEY = 'x-access-token'
export const HISTORY_KEY = 'history-key'
