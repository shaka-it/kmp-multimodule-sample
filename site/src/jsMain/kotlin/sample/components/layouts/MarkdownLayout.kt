package sample.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.OverflowWrap
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.overflowWrap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import com.varabyte.kobwebx.markdown.markdown
import sample.toSitePalette
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

val MarkdownStyle = CssStyle {
    base { Modifier.fillMaxSize() }

    cssRule("h1") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(400)
            .margin(bottom = 2.5.cssRem)
            .lineHeight(1.2)
    }

    cssRule("h2") {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(300)
            .margin(topBottom = 2.cssRem)
    }

    cssRule("h3") {
        Modifier
            .fontSize(2.4.cssRem)
            .fontWeight(300)
            .margin(topBottom = 1.5.cssRem)
    }

    cssRule("h4") {
        Modifier
            .fontSize(1.2.cssRem)
            .fontWeight(FontWeight.Bolder)
            .margin(top = 1.cssRem, bottom = 0.5.cssRem)
    }

    cssRule("ul") {
        Modifier.fillMaxWidth().overflowWrap(OverflowWrap.BreakWord)
    }

    cssRule(" :is(li,ol,ul)") {
        Modifier.margin(bottom = 0.25.cssRem)
    }

    cssRule("code") {
        Modifier
            .color(colorMode.toPalette().color.toRgb().copyf(alpha = 0.8f))
            .fontWeight(FontWeight.Bolder)
    }

    cssRule("pre") {
        Modifier
            .margin(top = 0.5.cssRem, bottom = 2.cssRem)
            .fillMaxWidth()
    }
    cssRule("pre > code") {
        Modifier
            .display(DisplayStyle.Block)
            .fillMaxWidth()
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .border(1.px, LineStyle.Solid, colorMode.toPalette().color)
            .borderRadius(0.25.cssRem)
            .padding(0.5.cssRem)
            .fontSize(1.cssRem)
            .overflow { x(Overflow.Auto) }
    }
}

@InitRoute
fun initMarkdownLayout(ctx: InitRouteContext) {
    val title = ctx.markdown!!.frontMatter["title"]?.singleOrNull()
    require(title != null) { "Markdown file must set \"title\" in frontmatter" }

    ctx.data.add(PageLayoutData(title))
}

@Composable
@Layout(".components.layouts.PageLayout")
fun MarkdownLayout(content: @Composable () -> Unit) {
    Div(MarkdownStyle.toAttrs()) {
        content()
    }
}
