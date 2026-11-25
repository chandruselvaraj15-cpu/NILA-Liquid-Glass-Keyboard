package com.nila.keyboard
import android.content.Context
import android.content.SharedPreferences
class NilaPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    var advancedMode: Boolean
        get() = prefs.getBoolean(KEY_ADVANCED_MODE, false)
        set(value) = prefs.edit().putBoolean(KEY_ADVANCED_MODE, value).apply()
    var darkTheme: Boolean
        get() = prefs.getBoolean(KEY_DARK_THEME, true)
        set(value) = prefs.edit().putBoolean(KEY_DARK_THEME, value).apply()
    // 10..95 (%)
    var keyboardTransparency: Int
        get() = prefs.getInt(KEY_TRANSPARENCY, 58).coerceIn(10, 95)
        set(value) = prefs.edit().putInt(KEY_TRANSPARENCY, value.coerceIn(10, 95)).apply()
    // 75..120 (%)
    var keySizeScale: Int
        get() = prefs.getInt(KEY_KEY_SIZE, 100).coerceIn(75, 120)
        set(value) = prefs.edit().putInt(KEY_KEY_SIZE, value.coerceIn(75, 120)).apply()
    // 0..100 (%)
    var keyRoundness: Int
        get() = prefs.getInt(KEY_KEY_ROUNDNESS, 65).coerceIn(0, 100)
        set(value) = prefs.edit().putInt(KEY_KEY_ROUNDNESS, value.coerceIn(0, 100)).apply()
    // 1..8 (dp)
    var keySpacing: Int
        get() = prefs.getInt(KEY_KEY_SPACING, 3).coerceIn(1, 8)
        set(value) = prefs.edit().putInt(KEY_KEY_SPACING, value.coerceIn(1, 8)).apply()
    // 0 = Liquid Glass, 1 = Matte Frosted, 2 = Soft 3D Crystal
    var keyTexture: Int
        get() = prefs.getInt(KEY_KEY_TEXTURE, 0).coerceIn(0, 2)
        set(value) = prefs.edit().putInt(KEY_KEY_TEXTURE, value.coerceIn(0, 2)).apply()
    // 0..100 (%)
    var glassBlurLevel: Int
        get() = prefs.getInt(KEY_GLASS_BLUR, 70).coerceIn(0, 100)
        set(value) = prefs.edit().putInt(KEY_GLASS_BLUR, value.coerceIn(0, 100)).apply()
    // 0..100 (%)
    var wallpaperVisibility: Int
        get() = prefs.getInt(KEY_WALLPAPER_VISIBILITY, 65).coerceIn(0, 100)
        set(value) = prefs.edit().putInt(KEY_WALLPAPER_VISIBILITY, value.coerceIn(0, 100)).apply()
    // 0..100 (%)
    var animIntensity: Int
        get() = prefs.getInt(KEY_ANIM_INTENSITY, 75).coerceIn(0, 100)
        set(value) = prefs.edit().putInt(KEY_ANIM_INTENSITY, value.coerceIn(0, 100)).apply()
    var rippleEnabled: Boolean
        get() = prefs.getBoolean(KEY_RIPPLE_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_RIPPLE_ENABLED, value).apply()
    var pop3dEnabled: Boolean
        get() = prefs.getBoolean(KEY_POP_3D_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_POP_3D_ENABLED, value).apply()
    var hapticEnabled: Boolean
        get() = prefs.getBoolean(KEY_HAPTIC_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_HAPTIC_ENABLED, value).apply()
    // 10..100 (%)
    var hapticIntensity: Int
        get() = prefs.getInt(KEY_HAPTIC_INTENSITY, 55).coerceIn(10, 100)
        set(value) = prefs.edit().putInt(KEY_HAPTIC_INTENSITY, value.coerceIn(10, 100)).apply()
    // --- Phase 2: Floating Bar ---
    var floatingBarEnabled: Boolean
        get() = prefs.getBoolean(KEY_FLOATING_BAR_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_FLOATING_BAR_ENABLED, value).apply()
    var floatingBarCollapsed: Boolean
        get() = prefs.getBoolean(KEY_FLOATING_BAR_COLLAPSED, false)
        set(value) = prefs.edit().putBoolean(KEY_FLOATING_BAR_COLLAPSED, value).apply()
    var floatingBarOffsetX: Float
        get() = prefs.getFloat(KEY_FLOATING_BAR_X, 0f)
        set(value) = prefs.edit().putFloat(KEY_FLOATING_BAR_X, value).apply()
    // --- Phase 2: One-Hand Mode (0 = Full, 1 = Left, 2 = Right) ---
    var oneHandMode: Int
        get() = prefs.getInt(KEY_ONE_HAND_MODE, 0).coerceIn(0, 2)
        set(value) = prefs.edit().putInt(KEY_ONE_HAND_MODE, value.coerceIn(0, 2)).apply()
    // 68..92 (%)
    var oneHandWidthPercent: Int
        get() = prefs.getInt(KEY_ONE_HAND_WIDTH, 80).coerceIn(68, 92)
        set(value) = prefs.edit().putInt(KEY_ONE_HAND_WIDTH, value.coerceIn(68, 92)).apply()
    // --- Phase 2: Languages & Gestures ---
    // 0 = English, 1 = Tamil, 2 = Tanglish
    var activeLanguage: Int
        get() = prefs.getInt(KEY_ACTIVE_LANGUAGE, 0).coerceIn(0, 2)
        set(value) = prefs.edit().putInt(KEY_ACTIVE_LANGUAGE, value.coerceIn(0, 2)).apply()
    var tanglishAutoConvert: Boolean
        get() = prefs.getBoolean(KEY_TANGLISH_AUTO, false)
        set(value) = prefs.edit().putBoolean(KEY_TANGLISH_AUTO, value).apply()
    var gesturesEnabled: Boolean
        get() = prefs.getBoolean(KEY_GESTURES_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_GESTURES_ENABLED, value).apply()
    // 0 = Switch Language on Spacebar Swipe, 1 = Move Cursor on Spacebar Swipe
    var spacebarSwipeMode: Int
        get() = prefs.getInt(KEY_SPACE_SWIPE_MODE, 0).coerceIn(0, 1)
        set(value) = prefs.edit().putInt(KEY_SPACE_SWIPE_MODE, value.coerceIn(0, 1)).apply()
    fun configHash(): Int {
        return listOf(
            advancedMode,
            darkTheme,
            keyboardTransparency,
            keySizeScale,
            keyRoundness,
            keySpacing,
            keyTexture,
            glassBlurLevel,
            wallpaperVisibility,
            animIntensity,
            rippleEnabled,
            pop3dEnabled,
            hapticEnabled,
            hapticIntensity,
            floatingBarEnabled,
            floatingBarCollapsed,
            oneHandMode,
            oneHandWidthPercent,
            activeLanguage,
            tanglishAutoConvert,
            gesturesEnabled,
            spacebarSwipeMode
        ).hashCode()
    }
    fun saveSnapshot() {
        prefs.edit()
            .putBoolean(SNAP_PREFIX + KEY_ADVANCED_MODE, advancedMode)
            .putBoolean(SNAP_PREFIX + KEY_DARK_THEME, darkTheme)
            .putInt(SNAP_PREFIX + KEY_TRANSPARENCY, keyboardTransparency)
            .putInt(SNAP_PREFIX + KEY_KEY_SIZE, keySizeScale)
            .putInt(SNAP_PREFIX + KEY_KEY_ROUNDNESS, keyRoundness)
            .putInt(SNAP_PREFIX + KEY_KEY_SPACING, keySpacing)
            .putInt(SNAP_PREFIX + KEY_KEY_TEXTURE, keyTexture)
            .putInt(SNAP_PREFIX + KEY_GLASS_BLUR, glassBlurLevel)
            .putInt(SNAP_PREFIX + KEY_WALLPAPER_VISIBILITY, wallpaperVisibility)
            .putInt(SNAP_PREFIX + KEY_ANIM_INTENSITY, animIntensity)
            .putBoolean(SNAP_PREFIX + KEY_RIPPLE_ENABLED, rippleEnabled)
            .putBoolean(SNAP_PREFIX + KEY_POP_3D_ENABLED, pop3dEnabled)
            .putBoolean(SNAP_PREFIX + KEY_HAPTIC_ENABLED, hapticEnabled)
            .putInt(SNAP_PREFIX + KEY_HAPTIC_INTENSITY, hapticIntensity)
            .putBoolean(KEY_HAS_SNAPSHOT, true)
            .apply()
    }
    fun hasSnapshot(): Boolean = prefs.getBoolean(KEY_HAS_SNAPSHOT, false)
    fun restoreSnapshot(): Boolean {
        if (!hasSnapshot()) return false
        prefs.edit()
            .putBoolean(KEY_ADVANCED_MODE, prefs.getBoolean(SNAP_PREFIX + KEY_ADVANCED_MODE, false))
            .putBoolean(KEY_DARK_THEME, prefs.getBoolean(SNAP_PREFIX + KEY_DARK_THEME, true))
            .putInt(KEY_TRANSPARENCY, prefs.getInt(SNAP_PREFIX + KEY_TRANSPARENCY, 58))
            .putInt(KEY_KEY_SIZE, prefs.getInt(SNAP_PREFIX + KEY_KEY_SIZE, 100))
            .putInt(KEY_KEY_ROUNDNESS, prefs.getInt(SNAP_PREFIX + KEY_KEY_ROUNDNESS, 65))
            .putInt(KEY_KEY_SPACING, prefs.getInt(SNAP_PREFIX + KEY_KEY_SPACING, 3))
            .putInt(KEY_KEY_TEXTURE, prefs.getInt(SNAP_PREFIX + KEY_KEY_TEXTURE, 0))
            .putInt(KEY_GLASS_BLUR, prefs.getInt(SNAP_PREFIX + KEY_GLASS_BLUR, 70))
            .putInt(KEY_WALLPAPER_VISIBILITY, prefs.getInt(SNAP_PREFIX + KEY_WALLPAPER_VISIBILITY, 65))
            .putInt(KEY_ANIM_INTENSITY, prefs.getInt(SNAP_PREFIX + KEY_ANIM_INTENSITY, 75))
            .putBoolean(KEY_RIPPLE_ENABLED, prefs.getBoolean(SNAP_PREFIX + KEY_RIPPLE_ENABLED, true))
            .putBoolean(KEY_POP_3D_ENABLED, prefs.getBoolean(SNAP_PREFIX + KEY_POP_3D_ENABLED, true))
            .putBoolean(KEY_HAPTIC_ENABLED, prefs.getBoolean(SNAP_PREFIX + KEY_HAPTIC_ENABLED, true))
            .putInt(KEY_HAPTIC_INTENSITY, prefs.getInt(SNAP_PREFIX + KEY_HAPTIC_INTENSITY, 55))
            .apply()
        return true
    }
    fun resetToDefaults() {
        prefs.edit()
            .putBoolean(KEY_DARK_THEME, true)
            .putInt(KEY_TRANSPARENCY, 58)
            .putInt(KEY_KEY_SIZE, 100)
            .putInt(KEY_KEY_ROUNDNESS, 65)
            .putInt(KEY_KEY_SPACING, 3)
            .putInt(KEY_KEY_TEXTURE, 0)
            .putInt(KEY_GLASS_BLUR, 70)
            .putInt(KEY_WALLPAPER_VISIBILITY, 65)
            .putInt(KEY_ANIM_INTENSITY, 75)
            .putBoolean(KEY_RIPPLE_ENABLED, true)
            .putBoolean(KEY_POP_3D_ENABLED, true)
            .putBoolean(KEY_HAPTIC_ENABLED, true)
            .putInt(KEY_HAPTIC_INTENSITY, 55)
            .apply()
    }
    companion object {
        private const val PREFS_NAME = "nila_keyboard_prefs"
        private const val SNAP_PREFIX = "snap_"
        private const val KEY_HAS_SNAPSHOT = "has_snapshot"
        private const val KEY_ADVANCED_MODE = "advanced_mode"
        private const val KEY_DARK_THEME = "dark_theme"
        private const val KEY_TRANSPARENCY = "keyboard_transparency"
        private const val KEY_KEY_SIZE = "key_size_scale"
        private const val KEY_KEY_ROUNDNESS = "key_roundness"
        private const val KEY_KEY_SPACING = "key_spacing"
        private const val KEY_KEY_TEXTURE = "key_texture"
        private const val KEY_GLASS_BLUR = "glass_blur_level"
        private const val KEY_WALLPAPER_VISIBILITY = "wallpaper_visibility"
        private const val KEY_ANIM_INTENSITY = "anim_intensity"
        private const val KEY_RIPPLE_ENABLED = "ripple_enabled"
        private const val KEY_POP_3D_ENABLED = "pop_3d_enabled"
        private const val KEY_HAPTIC_ENABLED = "haptic_enabled"
        private const val KEY_HAPTIC_INTENSITY = "haptic_intensity"
        private const val KEY_FLOATING_BAR_ENABLED = "floating_bar_enabled"
        private const val KEY_FLOATING_BAR_COLLAPSED = "floating_bar_collapsed"
        private const val KEY_FLOATING_BAR_X = "floating_bar_x"
        private const val KEY_ONE_HAND_MODE = "one_hand_mode"
        private const val KEY_ONE_HAND_WIDTH = "one_hand_width"
        private const val KEY_ACTIVE_LANGUAGE = "active_language"
        private const val KEY_TANGLISH_AUTO = "tanglish_auto_convert"
        private const val KEY_GESTURES_ENABLED = "gestures_enabled"
        private const val KEY_SPACE_SWIPE_MODE = "space_swipe_mode"
    }
}
