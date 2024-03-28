package com.nighttwo1.presentation.res.vector

import androidx.compose.ui.graphics.vector.ImageVector
import com.nighttwo1.presentation.res.vector.myiconpack.IcNewBox
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(IcNewBox)
    return __AllIcons!!
  }
