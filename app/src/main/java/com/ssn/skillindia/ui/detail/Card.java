/*
 * Skill India
 * Copyright (C) 2017  e-LEMON-ators
 *
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.ssn.skillindia.ui.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Card {
    @Nullable
    public final String title;
    @NonNull
    public final String content;
    @Nullable
    public final String action;

    public Card(@Nullable String title, @NonNull String content, @Nullable String action) {
        this.title = title;
        this.content = content;
        this.action = action;
    }
}
