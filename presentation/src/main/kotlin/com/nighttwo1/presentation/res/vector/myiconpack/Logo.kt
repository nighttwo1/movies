package com.nighttwo1.presentation.res.vector.myiconpack

import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.nighttwo1.presentation.res.vector.MyIconPack

public val MyIconPack.Logo: ImageVector
    get() {
        if (_logo != null) {
            return _logo!!
        }
        _logo = Builder(name = "Logo", defaultWidth = 50.0.dp, defaultHeight = 50.0.dp,
                viewportWidth = 50.0f, viewportHeight = 50.0f).apply {
            group {
                path(fill = null, stroke = null, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                        strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(-1.0f, 0.0f)
                    horizontalLineToRelative(52.3566f)
                    verticalLineToRelative(50.0f)
                    horizontalLineToRelative(-52.3566f)
                    close()
                }
            }
        }
        .build()
        return _logo!!
    }

private var _logo: ImageVector? = null
