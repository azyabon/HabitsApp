package com.azyabon.habits.core.designsystem.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> AppDropdown(
    label: String,
    options: List<Option<T>>,
    selectedValue: T?,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    error: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }
    var rowSize by remember { mutableStateOf(Size.Zero) }

    val interactionSource = remember { MutableInteractionSource() }

    val currentOptionLabel =
        options
            .firstOrNull { option -> option.value == selectedValue }
            ?.label
            ?: ""

    val outlinedColors = OutlinedTextFieldDefaults.colors()

    val borderColor =
        if (error != null) {
            MaterialTheme.colorScheme.error
        } else if (expanded) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.outline
        }

    val arrowRotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ArrowRotation",
    )

    Column(modifier = modifier) {
        Text(
            text = label,
            modifier = Modifier.padding(bottom = 4.dp),
        )

        Box {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            rowSize = coordinates.size.toSize()
                        }.clip(RoundedCornerShape(8.dp))
                        .background(outlinedColors.unfocusedContainerColor)
                        .border(
                            width = if (expanded) 2.dp else 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(8.dp),
                        ).clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            expanded = !expanded
                        }.padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (currentOptionLabel.isNotEmpty()) {
                    Text(
                        text = currentOptionLabel,
                        color = outlinedColors.focusedTextColor,
                    )
                } else {
                    Text(
                        text = placeholder,
                        color = outlinedColors.unfocusedPlaceholderColor,
                    )
                }

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expanded list",
                    modifier = Modifier.rotate(arrowRotationAngle),
                    tint = outlinedColors.unfocusedTrailingIconColor,
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier =
                    Modifier
                        .width(with(LocalDensity.current) { rowSize.width.toDp() })
                        .background(MaterialTheme.colorScheme.surfaceContainer),
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(text = option.label)
                        },
                        onClick = {
                            onOptionSelected(option.value)
                            expanded = false
                        },
                    )
                }
            }
        }

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
            )
        }
    }
}
