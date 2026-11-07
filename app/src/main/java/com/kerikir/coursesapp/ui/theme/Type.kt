package com.kerikir.coursesapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Набор типографики Material 2
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Другие стандартные стили Material 2, которые можно переопределить:
    h1 = TextStyle(...),
    h2 = TextStyle(...),
    h3 = TextStyle(...),
    h4 = TextStyle(...),
    h5 = TextStyle(...),
    h6 = TextStyle(...), // Аналог titleLarge из M3
    subtitle1 = TextStyle(...),
    subtitle2 = TextStyle(...),
    body2 = TextStyle(...),
    button = TextStyle(...),
    caption = TextStyle(...), // Аналог labelSmall из M3
    overline = TextStyle(...)
    */
)