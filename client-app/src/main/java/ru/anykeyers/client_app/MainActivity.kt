package ru.anykeyers.client_app

import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import ru.anykeyers.client_app.cache.FilterOrdersCache
import ru.anykeyers.client_app.fragment.FavoriteOrdersFragment
import ru.anykeyers.client_app.fragment.FilterOrdersFragment
import ru.anykeyers.client_app.fragment.HomeFragment
import ru.anykeyers.client_app.fragment.OrderFragment
import ru.anykeyers.client_app.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private var menu: Menu? = null
    private val filterCache: FilterOrdersCache by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_orders -> {
                    loadFragment(OrderFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // Создаем меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        this.menu = menu
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    fun updateFilterBadge() {
        val filterActive = filterCache.hasFilters  // Проверяем, активированы ли фильтры
        val menuItem = menu?.findItem(R.id.action_more)  // Находим пункт меню

        // Находим ImageView, который отображает иконку (три точки)
        val iconView = menuItem?.icon

        // Устанавливаем бейдж на иконке
        if (filterActive) {
            // Например, можно использовать setTint для изменения цвета иконки или показывать точку
            menuItem?.icon?.setTint(Color.RED)  // Здесь можно изменить цвет иконки или установить бейдж
        } else {
            // Если фильтры не активны, можно вернуть оригинальный цвет и скрыть точку
            menuItem?.icon?.setTint(Color.BLACK)
        }
    }

    // Обрабатываем нажатие на кнопку меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more -> {
                showPopupMenu(findViewById(R.id.toolbar))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Показываем PopupMenu
    private fun showPopupMenu(anchor: android.view.View) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            popupMenu.gravity = android.view.Gravity.END
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_favorites -> {
                    openFavoritesFragment()
                    true
                }
                R.id.menu_filter -> {
                    openFilterFragment()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun openFilterFragment() {
        val fragment = FilterOrdersFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openFavoritesFragment() {
        val favoritesFragment = FavoriteOrdersFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, favoritesFragment)
            .addToBackStack(null)
            .commit()
    }
}