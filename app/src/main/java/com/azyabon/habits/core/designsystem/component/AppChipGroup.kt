package com.azyabon.habits.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> AppChipGroup(
    modifier: Modifier = Modifier,
    options: List<Option<T>>,
    selectedValues: Set<T> = emptySet(),
    onOptionClick: (T) -> Unit,
    label: String? = null,
    error: String? = null,
) {
    Column(modifier = modifier) {
        label?.let {
            Text(
                text = it,
                modifier = Modifier.padding(bottom = 2.dp, start = 16.dp),
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(options) { option ->
                FilterChip(
                    selected = option.value in selectedValues,
                    onClick = { onOptionClick(option.value) },
                    label = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            option.icon?.let {
                                Image(
                                    imageVector = it,
                                    contentDescription = null,
                                    modifier =
                                        Modifier
                                            .size(32.dp)
                                            .padding(top = 4.dp),
                                )
                            }
                            Text(
                                text = option.label,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(vertical = 4.dp),
                            )
                        }
                    },
                    modifier = Modifier.defaultMinSize(minWidth = 48.dp, minHeight = 48.dp),
                )
            }
        }

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}
