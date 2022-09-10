package com.lowersoft.waifupics.common

object Constants {
    object URLs {
        private const val BASE = "https://api.waifu.im"
        const val WAIFU = "$BASE/random/?selected_tags=waifu&many=true"
        const val NSFW = "$BASE/random/?is_nsfw=true&many=true"
        const val OPPAI = "$BASE/random/?selected_tags=oppai&many=true"
    }
}