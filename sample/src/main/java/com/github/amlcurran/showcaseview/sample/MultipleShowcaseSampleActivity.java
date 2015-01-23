/*
 * Copyright 2014 Alex Curran
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.amlcurran.showcaseview.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.ShowcaseViews;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MultipleShowcaseSampleActivity extends ActionBarActivity {

    ShowcaseViews mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_multiple_showcase);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.buttonLike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.like_message, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mViews = new ShowcaseViews(MultipleShowcaseSampleActivity.this,
                            new ShowcaseViews.OnShowcaseAcknowledged() {
                                @Override
                                public void onShowCaseAcknowledged(ShowcaseView showcaseView) {
                                    Toast.makeText(MultipleShowcaseSampleActivity.this, R.string.dismissed_message, Toast.LENGTH_SHORT).show();
                                }
                            });
                    mViews.addView(new ShowcaseViews.ItemViewProperties(new ViewTarget(findViewById(R.id.image)),
                            R.string.showcase_image_title,
                            R.string.showcase_image_message));

                    mViews.addView(new ShowcaseViews.ItemViewProperties(new ViewTarget(findViewById(R.id.buttonLike)),
                            R.string.showcase_like_title,
                            R.string.showcase_like_message));

                    mViews.show();
                }
            }, 300);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
