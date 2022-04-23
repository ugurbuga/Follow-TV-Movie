package com.ugurbuga.followtvmovie.domain.external.model

data class ExternalIdsUIModel(
    val facebookId: String? = null,
    val imdbId: String? = null,
    val instagramId: String? = null,
    val twitterId: String? = null
) {
    fun hasImdbId(): Boolean {
        return !imdbId.isNullOrBlank()
    }

    fun hasFacebookId(): Boolean {
        return !facebookId.isNullOrBlank()
    }

    fun hasTwitterId(): Boolean {
        return !twitterId.isNullOrBlank()
    }

    fun hasInstagramId(): Boolean {
        return !instagramId.isNullOrBlank()
    }

    fun getTwitterUrl(): String {
        return "https://twitter.com/${twitterId}"
    }

    fun getInstagramUrl(): String {
        return "https://www.instagram.com/${instagramId}/"
    }

    fun getImdbUrl(): String {
        return "https://www.imdb.com/title/${imdbId}/"
    }

    fun getFacebookUrl(): String {
        return "https://www.facebook.com/${facebookId}"
    }

    fun getFacebookDeeplink(): String {
        return "fb://facewebmodal/f?href=https://www.facebook.com/${facebookId}"
    }
}