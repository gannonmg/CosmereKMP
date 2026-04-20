package com.gannon.cosmere_kmp.rules.talent

import com.gannon.cosmere_kmp.util.DisplayNamedOption

/** Static talent JSON source that can derive its own bundled resource path. */
interface TalentResourceSource : DisplayNamedOption {
    /** Folder path under the shared talents resources root. */
    val resourceDirectory: String

    /** Bundled JSON file path for this source. */
    val resourcePath: String
        get() = "talents/$resourceDirectory/${displayName}.json"
}
