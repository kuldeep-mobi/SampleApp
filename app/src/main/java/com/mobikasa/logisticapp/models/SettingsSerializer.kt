package com.mobikasa.logisticapp.models

import androidx.datastore.core.Serializer
import com.mobikasa.logisticapp.SettingsProto
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<SettingsProto> {
    override val defaultValue: SettingsProto
        get() = SettingsProto.getDefaultInstance()

    override fun readFrom(input: InputStream): SettingsProto {
        return SettingsProto.parseFrom(input)
    }

    override fun writeTo(t: SettingsProto, output: OutputStream) {
        t.writeTo(output)
    }

}