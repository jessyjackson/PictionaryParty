package com.pictionaryparty.utils

import io.getstream.chat.android.client.models.Channel

inline val String.channelId
    get()="$CHANNEL_MESSAGING:$this"

inline val Channel.groupID
    get() = cid.split(":")[1]
inline val Channel.hostName :  String?
    get() = this.extraData[KEY_HOST_NAME].toString()